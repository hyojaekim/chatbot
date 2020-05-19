import axios from 'axios'
export default axios.create({
    baseURL: 'http://chatbotservice.info/api',
    timeout: 5000,
})