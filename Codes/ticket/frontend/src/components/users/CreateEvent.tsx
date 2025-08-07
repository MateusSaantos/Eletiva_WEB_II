import { useState } from "react";
import api from "../../services/api";

function CreateEvent() {
  const [description, setDescription] = useState("");
  const [type, setType] = useState("0");
  const [date, setDate] = useState("");
  const [startSales, setStartSales] = useState("");
  const [endSales, setEndSales] = useState("");
  const [price, setPrice] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const body = {
        description,
        type: Number(type),
        date,
        startSales,
        endSales,
        price: Number(price),
      };

      const response = await api("/sales/events", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(body),
      });

      alert("Evento cadastrado com sucesso!");
      console.log("Resposta:", response);
    } catch (err) {
      console.error(err);
      alert("Erro ao cadastrar evento");
    }
  };

  return (
    <div>
      <h2>Cadastro de Evento</h2>
      <form onSubmit={handleSubmit}>
        <input placeholder="Descrição" value={description} onChange={(e) => setDescription(e.target.value)} />

        <select value={type} onChange={(e) => setType(e.target.value)}>
          <option value="0">Show</option>
          <option value="1">Teatro</option>
          <option value="2">Palestra</option>
        </select>

        <input type="datetime-local" value={date} onChange={(e) => setDate(e.target.value)} />
        <input type="datetime-local" value={startSales} onChange={(e) => setStartSales(e.target.value)} />
        <input type="datetime-local" value={endSales} onChange={(e) => setEndSales(e.target.value)} />
        <input type="number" placeholder="Preço" value={price} onChange={(e) => setPrice(e.target.value)} />

        <button type="submit">Cadastrar</button>
      </form>
    </div>
  );
}

export default CreateEvent;
