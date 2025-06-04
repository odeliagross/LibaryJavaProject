import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const getAllCustomers = () => axios.get(`${BASE_URL}/getAllCustomers/`);
export const getCustomerById = (id) => axios.get(`${BASE_URL}/getCustomerById/${id}`);
export const addCustomer = (customer) => axios.post(`${BASE_URL}/addCustomer/`, customer);
