import React, { useEffect, useState } from "react";
import { getAllCustomers, addCustomer } from '../service/customerService';

export default function CustomerList() {
  const [customers, setCustomers] = useState([]);
  const [name, setName] = useState(""); // לדוגמה שם לקוח חדש
  const [id, setId] = useState(""); // לדוגמה תעודת זהות

  useEffect(() => {
    fetchCustomers();
  }, []);

  const fetchCustomers = async () => {
    try {
      const res = await getAllCustomers();
      setCustomers(res.data);
    } catch (err) {
      console.error("Error fetching customers:", err);
    }
  };

  const handleAddCustomer = async () => {
    try {
      const res = await addCustomer({ id, name });
      if (res.data) {
        alert("Customer added");
        fetchCustomers();
      }
    } catch (err) {
      console.error("Error adding customer:", err);
    }
  };

  return (
    <div>
      <h2 className="text-xl font-semibold mb-2">Customers</h2>
      <input value={id} onChange={(e) => setId(e.target.value)} placeholder="Customer ID" />
      <input value={name} onChange={(e) => setName(e.target.value)} placeholder="Customer Name" />
      <button onClick={handleAddCustomer}>Add Customer</button>

      <ul>
        {customers.map((c, i) => (
          <li key={i}>
            {c.name} — ID: {c.id}
          </li>
        ))}
      </ul>
    </div>
  );
}
