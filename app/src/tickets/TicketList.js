import React, { Component } from "react";
import { Link } from "react-router-dom";
import Navbar from "../common/Navbar";

class TicketList extends Component {
  constructor(params) {
    super(params);
    this.state = { groups: [], isLoading: true };
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({ isLoading: true });

    fetch("/api/tickets")
      .then(response => response.json())
      .then(data => this.setState({ tickets: data, isLoading: false }));
  }

  async remove(id) {
    await fetch(`/api/ticket/${id}`, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    }).then(() => {
      let updatedTickets = [...this.state.tickets].filter(i => i.id !== id);
      this.setState({ tickets: updatedTickets });
    });
  }

  render() {
    const { tickets, isLoading } = this.state;

    if (isLoading) {
      return (
        <div>
          <Navbar />
          <p>Loading...</p>
        </div>
      );
    }

    if (tickets.length === 0) {
      return (
        <div>
          <Navbar />
          <p>No tickets in the DB!</p>
          <Link to="/tickets/new">Add Ticket</Link>
        </div>
      );
    }

    const ticketList = tickets.map(ticket => {
      return (
        <tr key={ticket.id}>
          <td>{ticket.id}</td>
          <td>{ticket.summary}</td>
          <td>{ticket.body}</td>
          <td>{ticket.requester.firstName + " " + ticket.requester.lastName}</td>
          <td>
            {ticket.assignee == null
              ? ""
              : ticket.assignee.firstName + " " + ticket.assignee.lastName}
          </td>
          <td>
            <Link to={"/tickets/" + ticket.id}>Edit</Link>
            <button onClick={() => this.remove(ticket.id)}>Delete</button>
          </td>
        </tr>
      );
    });

    return (
      <div>
        <Navbar />
        <Link to="/tickets/new">Add Ticket</Link>
        <h3>Ticket List</h3>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Summary</th>
              <th>Body</th>
              <th>Requester</th>
              <th>Assignee</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>{ticketList}</tbody>
        </table>
      </div>
    );
  }
}

export default TicketList;
