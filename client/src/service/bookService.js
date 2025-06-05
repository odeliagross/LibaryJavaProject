import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const getAllBooks = () => axios.get(`${BASE_URL}/getAllBooks/`);
export const getBookById = (id) => axios.get(`${BASE_URL}/getBookById/${id}`);
export const addBook = (book) => axios.post(`${BASE_URL}/addBook/`, book);
export const updateBook = (id, name) => axios.patch(`${BASE_URL}/updateBook/${id}`,{"bookName":name});
export const findBookByName = (name) => axios.get(`${BASE_URL}/findBookByName/${name}`);
export const findBookByYear = (year) => axios.get(`${BASE_URL}/findBookByYear/${year}`);