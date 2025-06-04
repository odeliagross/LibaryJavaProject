import React, { useEffect, useState } from "react";
import { getAllLendings, addLending, updateReturned } from "../service/lendingService";

export default function LendingList() {
  const [lendings, setLendings] = useState([]);
  const [customerId, setCustomerId] = useState("");
  const [bookId, setBookId] = useState("");
  const [lendingDate, setLendingDate] = useState("");

  useEffect(() => {
    fetchLendings();
  }, []);

  const fetchLendings = async () => {
    try {
      const res = await getAllLendings();
      setLendings(res.data);
    } catch (err) {
      console.error("Error fetching lendings:", err);
    }
  };

  const handleUpdateReturned = async (id) => {
    try {
      const res = await updateReturned(id);
      if (res.data) {
        alert("Updated successfully");
        fetchLendings();
      }
    } catch (err) {
      console.error("Error updating lending:", err);
    }
  };

  const handleAddLending = async () => {
    try {
      const newLending = { customerId, bookId, lendingDate };
      const res = await addLending(newLending);
      if (res.data) {
        alert("Lending added");
        setCustomerId("");
        setBookId("");
        setLendingDate("");
        fetchLendings();
      }
    } catch (err) {
      console.error("Error adding lending:", err);
    }
  };

  return (
    <div>
      <h2 className="text-xl font-semibold mb-2">Lendings</h2>

      <div className="mb-4">
        <input
          placeholder="Customer ID"
          value={customerId}
          onChange={(e) => setCustomerId(e.target.value)}
          className="border p-2 mr-2"
        />
        <input
          placeholder="Book ID"
          value={bookId}
          onChange={(e) => setBookId(e.target.value)}
          className="border p-2 mr-2"
        />
        <input
          type="date"
          value={lendingDate}
          onChange={(e) => setLendingDate(e.target.value)}
          className="border p-2 mr-2"
        />
        <button onClick={handleAddLending} className="bg-blue-500 text-white px-4 py-2 rounded">
          Add Lending
        </button>
      </div>

      <ul>
        {lendings.map((l, i) => (
          <li key={i}>
            Lending ID: {l.id} — Customer ID: {l.customerId} — Returned: {l.returned ? "Yes" : "No"}
            {!l.returned && (
              <button
                onClick={() => handleUpdateReturned(l.id)}
                className="ml-2 bg-green-500 text-white px-2 rounded"
              >
                Mark as Returned
              </button>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
