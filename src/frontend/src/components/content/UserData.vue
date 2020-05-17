<template>
    <v-card width="70%">
        <v-card-title>
            사용자 데이터
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="desserts"
                :search="search"
        ></v-data-table>
    </v-card>
</template>

<script>
    export default {
        name: "UserData",
        data () {
            return {
                search: '',
                headers: [
                    {
                        text: '발화',
                        align: 'start',
                        sortable: false,
                        value: 'text',
                    },
                    { text: '유형', value: 'type' },
                    { text: '동의어', value: 'synonym' },
                    { text: '조회수', value: 'count' },
                ],
                desserts: [
                    {
                        text: '지하철 언제와?',
                        type: '전철',
                        synonym: '지하철',
                        count: 13,
                    },
                    {
                        text: '전철 언제와?',
                        type: '전철',
                        synonym: '전철',
                        count: 25,
                    },
                    {
                        text: '전철언제',
                        type: '전철',
                        synonym: '전철',
                        count: 10,
                    },
                    {
                        text: '학식뭐냐',
                        type: '학식',
                        synonym: '학식',
                        count: 5,
                    },
                    {
                        text: '밥 뭐나옴',
                        type: '학식',
                        synonym: '밥',
                        count: 9,
                    },
                    {
                        text: '마스크 재고좀',
                        type: '마스크 재고',
                        synonym: '마스크 재고',
                        count: 3,
                    },
                    {
                        text: '오늘의 학식은?',
                        type: '학식',
                        synonym: '학식',
                        count: 9,
                    },
                    {
                        text: '지하철?',
                        type: '전철',
                        synonym: '지하철',
                        count: 2,
                    },
                    {
                        text: '전철은?',
                        type: '전철',
                        synonym: '전철',
                        count: 12,
                    },
                ],
            }
        },
    }
</script>

<style scoped>

</style>