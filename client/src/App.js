import React, { useState } from "react";
import BookList from './components/BooksList'
import CustomerList from './components/CustomersList'
import LendingList from './components/LendingList'

export default function App() {
  const [activeTab, setActiveTab] = useState("books");

  return (
    <div className="p-6 max-w-4xl mx-auto">
      <h1 className="text-3xl font-bold mb-6 text-center">Library System</h1>

      {/* כפתורי ניווט (טאבים) */}
      <div className="flex space-x-4 mb-6 justify-center">
        <button
          className={`px-4 py-2 rounded ${
            activeTab === "books" ? "bg-blue-600 text-white" : "bg-gray-200"
          }`}
          onClick={() => setActiveTab("books")}
        >
          Books
        </button>
        <button
          className={`px-4 py-2 rounded ${
            activeTab === "customers" ? "bg-blue-600 text-white" : "bg-gray-200"
          }`}
          onClick={() => setActiveTab("customers")}
        >
          Customers
        </button>
        <button
          className={`px-4 py-2 rounded ${
            activeTab === "lendings" ? "bg-blue-600 text-white" : "bg-gray-200"
          }`}
          onClick={() => setActiveTab("lendings")}
        >
          Lendings
        </button>
      </div>

      {/* הצגת הקומפוננטה לפי הטאב שנבחר */}
      <div>
        {activeTab === "books" && <BookList />}
        {activeTab === "customers" && <CustomerList />}
        {activeTab === "lendings" && <LendingList />}
      </div>
    </div>
  );
}
