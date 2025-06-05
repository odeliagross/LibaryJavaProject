import React, { useEffect, useState } from "react";
import { getAllLendings, addLending, updateReturned } from "../service/lendingService";

export default function LendingList() {
  const [lendings, setLendings] = useState([]);
  const [customerId, setCustomerId] = useState("");
  const [bookId, setBookId] = useState("");
  const [lendingDate, setLendingDate] = useState(new Date().toISOString().split('T')[0]);
  const [isReturned,setIsReturned]=useState(false);

  useEffect(() => {
    fetchLendings();
  }, []);

  const fetchLendings = async () => {
    try {
      const res = await getAllLendings();
          console.log("Lendings from server:", res.data); // ⬅ בדקי פה מה מופיע
      if (res && res.data) {
        setLendings(res.data);
      } else {
        console.error("No lendings data found");
      }
    } catch (err) {
      console.error("Error fetching lendings:", err);
    }
};

const handleUpdateReturned = async (id) => {
  if (!id || isNaN(id)) {
    alert("Invalid lending ID");
    return;}
  try {
    const res = await updateReturned(id);
    if (res && res.data) {
      alert("Updated successfully");
      fetchLendings();
    }
  } catch (err) {
    console.error("Error updating lending:", err);
    alert("Failed to update, please try again.");
  }
};


  const handleAddLending = async () => {
    if (!customerId || !bookId) {
      alert("Customer ID and Book ID are required!");
      return;
    }
    try {
      const newLending = { customerId, bookId, lendingDate, isReturned };
      const res = await addLending(newLending);
      if (res.data) {
        alert("Lending added");
        setCustomerId("");
        setBookId("");
        setLendingDate(new Date().toISOString().split('T')[0]);
        setIsReturned(false);
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
          type="Number"
          className="border p-2 mr-2"
        />
        
        <button onClick={handleAddLending} className="bg-blue-500 text-white px-4 py-2 rounded">
          Add Lending
        </button>
      </div>

      <ul>
        {lendings.map((l, i) => {
           console.log("Lending object:", l)
           return(
           
          <li key={i}>
            Lending ID: {l.id} | Book ID: {l.bookId} | Customer ID: {l.customerId} | Returned: {l.returned ? " Yes" : " No"}

            {!l.returned && (
              <button
                onClick={() => handleUpdateReturned(l.id)}
                className="ml-2 bg-green-500 text-white px-2 rounded"
              >
                Mark as Returned
              </button>
            )}
          </li>)}
        )}
      </ul>
    </div>
  );
}
