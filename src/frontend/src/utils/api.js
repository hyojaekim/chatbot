import axios from 'axios'

// export const BASE_URL = "http://localhost:9000";
export const BASE_URL = "http://chatbotservice.info";

export default axios.create({
    baseURL: `${this.BASE_URL}/api`,
    timeout: 5000,
})