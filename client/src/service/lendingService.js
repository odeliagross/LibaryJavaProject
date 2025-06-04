import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const getAllLendings = () => axios.get(`${BASE_URL}/getAllLendings/`);
export const getAllCustomerLendings = (id) => axios.get(`${BASE_URL}/getAllCustomerLendings/${id}`);
export const addLending = (lending) => axios.post(`${BASE_URL}/addLending/`, lending);
export const updateReturned = (id) => axios.patch(`${BASE_URL}/updateReturned/${id}`);
export const getFine = (id) => axios.get(`${BASE_URL}/getFine/${id}`);
