import React, { useEffect, useState } from "react";
import { getAllBooks, addBook } from '../service/bookService'

export default function BookList() {
  const [books, setBooks] = useState([]);
  const [bookName, setBookName] = useState("");
  const [authorId, setAuthorId] = useState("");
  const [publishDate, setPublishDate] = useState("");

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

  return (
    <div>
      <h2 className="text-xl font-semibold mb-2">Books</h2>
      <input value={bookName} onChange={(e) => setBookName(e.target.value)} placeholder="Book Name" />
      <input value={authorId} onChange={(e) => setAuthorId(e.target.value)} placeholder="Author ID" />
      <input type="date" value={publishDate} onChange={(e) => setPublishDate(e.target.value)} />
      <button onClick={handleAddBook}>Add Book</button>

      <ul>
        {books.map((b, i) => (
          <li key={i}>{b.bookName} — {b.publishDate}</li>
        ))}
      </ul>
    </div>
  );
}
