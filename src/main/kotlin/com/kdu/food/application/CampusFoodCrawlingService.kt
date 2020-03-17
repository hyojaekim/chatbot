package com.kdu.food.application

import com.kdu.common.util.SSLConnect
import com.kdu.food.domain.Cafeteria
import com.kdu.food.domain.CampusFood
import com.kdu.food.domain.CampusFoods
import com.kdu.food.exception.CampusFoodCrawlingFailException
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.stereotype.Service

@Service
class CampusFoodCrawlingService {

    fun crawling(cafeteria: Cafeteria): ArrayList<CampusFoods> {
        try {
            validateCafeteria(cafeteria)
            val document: Document = SSLConnect.getDocument(cafeteria.code)
            val foodTable: Element = findFoodTable(document)
            return findCampusFood(cafeteria, foodTable)
        } catch (e: Exception) {
            throw CampusFoodCrawlingFailException()
        }
    }

    private fun validateCafeteria(cafeteria: Cafeteria) {
        if (cafeteria == Cafeteria.EMPTY) {
            throw CampusFoodCrawlingFailException()
        }
    }

    private fun findFoodTable(document: Document): Element {
        return document.getElementsByClass("tbl-basic01")[FIRST]
    }

    private fun findCampusFood(cafeteria: Cafeteria, foodTable: Element): ArrayList<CampusFoods> {
        val result = arrayListOf<CampusFoods>()
        val date = getDate(foodTable)
        val foodContents = foodTable.getElementsByTag("tbody")[FIRST].getElementsByTag("tr")
        for (i in 0 until foodContents.size - 1) {
            val typeElement = foodContents[i].getElementsByTag("th")[FIRST]
            val foodContentElements = foodContents[i].getElementsByTag("td")

            val type: String = typeElement.html().replace("<br>", "")
            result.add(convertFoodTypes(cafeteria, date, type, foodContentElements))
        }
        return result
    }

    private fun getDate(foodTable: Element): ArrayList<String> {
        val result = arrayListOf<String>()
        val firstRowElement = foodTable.getElementsByTag("thead")[FIRST]
        val dateElements = firstRowElement.getElementsByClass("tlh130")

        for (i in 0 until dateElements.size) {
            val dayOfWeek: Char = dateElements[i].html()[FIRST]
            val date: String = dateElements[i].getElementsByClass("f11")[FIRST].html()
            result.add("$dayOfWeek/$date")
        }
        return result
    }

    private fun convertFoodTypes(cafeteria: Cafeteria, date: ArrayList<String>, type: String, foodContents: Elements): CampusFoods {
        validateFoodContents(foodContents)
        val result = arrayListOf<CampusFood>()
        for (i in 0 until foodContents.size) {
            val splitDate = date[i].split(DELIMITER)
            val convertDate = splitDate[FIRST]
            val dayOfWeek = splitDate[SECOND]
            val content = findFoodContent(foodContents[i])
            val campusFood = CampusFood.of(cafeteria, dayOfWeek, convertDate, type, content)
            result.add(campusFood)
        }
        return CampusFoods(result)
    }

    private fun validateFoodContents(foodContents: Elements) {
        if (foodContents.size != SEVEN) {
            throw IllegalArgumentException()
        }
    }

    private fun findFoodContent(food: Element): String {
        if (food.getElementsByTag("span").size != EMPTY) {
            return food.getElementsByTag("span")[FIRST].html()
        }
        return food.html()
    }

    companion object {
        private const val FIRST = 0
        private const val SECOND = 0
        private const val EMPTY = 0
        private const val SEVEN = 7
        private const val DELIMITER = "/"
    }
}