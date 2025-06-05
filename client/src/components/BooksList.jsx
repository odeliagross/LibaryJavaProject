import React, { useEffect, useState } from "react";
import {getAllBooks,addBook,findBookByYear,findBookByName,getBookById, updateBook} from '../service/bookService'

export default function BookList() {
  const [books, setBooks] = useState([]);
  const [bookName, setBookName] = useState("");
  const [authorId, setAuthorId] = useState("");
  const [publishDate, setPublishDate] = useState(new Date().toISOString().split('T')[0]);
  const [searchId, setSearchId] = useState("");
  const [searchName, setSearchName] = useState("");
  const [searchYear, setSearchYear] = useState("");
  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
      const res = await getAllBooks();
      setBooks(res.data);
    } catch (err) {
      console.error("Error fetching books:", err);
    }
  };

  const handleAddBook = async () => {
    try {
      const res = await addBook({ bookName, authorId, publishDate });
      if (res.data) {
        alert("Book added");
        fetchBooks();
      }
    } catch (err) {
      console.error("Error adding book:", err);
    }
  };

  const handleSearchById = async () => {
    try {
      const res = await getBookById(searchId);
      if (res.data) setBooks([res.data]);
      else alert("Book not found");
    } catch (err) {
      console.error("Error searching by ID:", err);
    }
  };
   const handleSearchByName = async () => {
    try {
      const res = await findBookByName(searchName);
      setBooks(res.data);
    } catch (err) {
      console.error("Error searching by name:", err);
    }
  };

  const handleSearchByYear = async () => {
    try {
      const res = await findBookByYear(searchYear);
      setBooks(res.data);
    } catch (err) {
      console.error("Error searching by year:", err);
    }
  };

 

  const handleUpdate = async (id) => {
    const newName = prompt("Enter new name for the book:");
    console.log(id)
    console.log(newName)
    if (!newName) return;
    try {
      await updateBook(id,newName);
      fetchBooks();
    } catch (err) {
      console.error("Error updating book:", err);
    }
  };

  return (
    <div>
      <h2 className="text-xl font-semibold mb-2">Books</h2>
      <div className="space-x-2 mt-4">
        <input value={searchId} onChange={(e) => setSearchId(e.target.value)} placeholder="Search by ID" />
        <button onClick={handleSearchById}>Search by ID</button>

        <input value={searchName} onChange={(e) => setSearchName(e.target.value)} placeholder="Search by Name" />
        <button onClick={handleSearchByName}>Search by Name</button>

        <input value={searchYear} onChange={(e) => setSearchYear(e.target.value)} placeholder="Search by Year" />
        <button onClick={handleSearchByYear}>Search by Year</button>
      </div>
      <input value={bookName} onChange={(e) => setBookName(e.target.value)} placeholder="Book Name" />
      <input value={authorId} onChange={(e) => setAuthorId(e.target.value)} placeholder="Author ID" />
      <input type="date" value={publishDate} onChange={(e) => setPublishDate(e.target.value)} />
      <button onClick={handleAddBook}>Add Book</button>
      <ul className="space-y-4">
          {books.map((book, index) => (
            <li key={index} className="border p-4 rounded shadow">
              <h3 className="text-lg font-semibold">{book.bookName}</h3>
              {console.log(book)}
              <button onClick={() => handleUpdate(book.id)} className="text-blue-600 ml-2">Update</button>
              <p>Published: {book.publishDate}</p>
              <p className="mt-2 font-medium">Lending Dates:</p>
              {book.lendingList && book.lendingList.length > 0 ? (
                <ul className="list-disc list-inside">
                  {book.lendingList.map((date, i) => (
                    <li key={i}>{date}</li>
                  ))}
                </ul>
              ) : (
                <p>No lendings yet.</p>
              )}
            </li>
          ))}
        </ul>
    </div>
  );
}
