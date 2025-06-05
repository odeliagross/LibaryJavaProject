import React, { useEffect, useState } from "react";
import { getAllCustomers, addCustomer } from '../service/customerService';

export default function CustomerList() {
  const [customers, setCustomers] = useState([]);
  const [firstName, setFirstName] = useState(""); 
  const [lastName, setLastName] = useState("")
  const[phone,setPhone]=useState("")
  const [id, setId] = useState("");

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
      const res = await addCustomer({ id, firstName,lastName,phone});
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
      <input value={firstName} onChange={(e) => setFirstName(e.target.value)} placeholder="First Name" />
      <input value={lastName} onChange={(e) => setLastName(e.target.value)} placeholder="Last Name" />
      <input value={phone} onChange={(e) => setPhone(e.target.value)} placeholder="Phone Number" />
      <button onClick={handleAddCustomer}>Add Customer</button>

      <ul>
       {customers.map((c, i) => (
        <li key={i} className="border p-3 my-2 rounded shadow">
          <p><strong>{c.firstName} {c.lastName}</strong></p>
          <p>Phone: {c.phone}</p>

          {c.lendingList && c.lendingList.length > 0 ? (
            <div className="ml-4 mt-2">
              <p className="font-semibold">Lendings:</p>
              <ul className="list-disc list-inside text-sm">
                {c.lendingList.map((l, j) => (
                  <li key={j}>
                    Book ID: {l.bookId} | Date: {l.lendingDate} | Returned: {l.returned ? "Yes" : "No"}
                  </li>
                ))}
              </ul>
            </div>
          ) : (
            <p className="text-gray-500 text-sm">No lendings</p>
          )}
        </li>
        ))}
      </ul>


    </div>
  );
}
