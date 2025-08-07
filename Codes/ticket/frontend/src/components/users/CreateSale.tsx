import { useEffect, useState } from "react";
import api from "../../services/api";

function CreateSale() {
  const [users, setUsers] = useState([]);
  const [events, setEvents] = useState([]);

  const [userId, setUserId] = useState("");
  const [eventId, setEventId] = useState("");
  const [saleStatus, setSaleStatus] = useState("EM_ABERTO");

  useEffect(() => {
    loadUsers();
    loadEvents();
  }, []);

  const loadUsers = async () => {
    try {
      const data = await api("/users");
      setUsers(data);
    } catch (err) {
      console.error("Erro ao carregar usuários", err);
    }
  };

  const loadEvents = async () => {
    try {
      const data = await api("/sales/events");
      setEvents(data);
    } catch (err) {
      console.error("Erro ao carregar eventos", err);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const body = {
      userId,
      eventId,
      saleStatus,
    };

    console.log("Enviando venda:", body);

    try {
      const response = await api("/sales", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
      });

      console.log("Resposta da API:", response);
      alert("Venda cadastrada com sucesso!");

      setUserId("");
      setEventId("");
      setSaleStatus("EM_ABERTO");
    } catch (err) {
      console.error("Erro ao cadastrar venda", err);
      alert("Erro ao cadastrar venda");
    }
  };

  return (
    <div>
      <h2>Cadastrar Venda</h2>

      <form onSubmit={handleSubmit}>
        <select value={userId} onChange={(e) => setUserId(e.target.value)} required>
          <option value="">Selecione o usuário</option>
          {users.map((user: any) => (
            <option key={user.id} value={user.id}>
              {user.name} ({user.email})
            </option>
          ))}
        </select>

        <select value={eventId} onChange={(e) => setEventId(e.target.value)} required>
          <option value="">Selecione o evento</option>
          {events.map((event: any) => (
            <option key={event.id} value={event.id}>
              {event.description} - R${event.price}
            </option>
          ))}
        </select>

        <select value={saleStatus} onChange={(e) => setSaleStatus(e.target.value)}>
          <option value="EM_ABERTO">Em Aberto</option>
          <option value="PAGO">Pago</option>
          <option value="CANCELADO">Cancelado</option>
          <option value="ESTORNADO">Estornado</option>
        </select>

        <button type="submit">Cadastrar Venda</button>
      </form>
    </div>
  );
}

export default CreateSale;
