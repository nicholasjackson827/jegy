import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";

class TicketEdit extends Component {
  emptyTicket = {
    summary: "",
    body: "",
    requesterUserId: "",
    assigneeUserId: ""
  };

  constructor(props) {
    super(props);
    this.state = {
      ticket: this.emptyTicket
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleRequesterChange = this.handleRequesterChange.bind(this);
    this.handleAssigneeChange = this.handleAssigneeChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    const id = this.props.match.params.id;
    console.log(id);
    if (id !== "new") {
      const data = await fetch(`/api/ticket/${id}`);
      const ticket = await data.json();
      this.setState({ ticket: ticket });
    }
  }

  handleChange(event) {
    const { value, name, type } = event.target;
    let ticket = { ...this.state.ticket };
    ticket[name] = type === "number" ? parseInt(value, 10) : value;
    this.setState({ ticket });
  }

  handleRequesterChange(event) {
    const { value } = event.target;
    let ticket = { ...this.state.ticket };
    ticket.requester = null;
    ticket.requesterUserId = parseInt(value, 10);
    this.setState({ ticket });
  }

  handleAssigneeChange(event) {
    const { value } = event.target;
    let ticket = { ...this.state.ticket };
    ticket.assignee = null;
    ticket.assigneeUserId = parseInt(value, 10);
    this.setState({ ticket });
  }

  async handleSubmit(event) {
    event.preventDefault();
    const { ticket } = this.state;

    await fetch("/api/ticket", {
      method: ticket.id ? "PUT" : "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(ticket)
    });
    this.props.history.push("/tickets");
  }

  render() {
    const { ticket } = this.state;
    const title = <h2>{ticket.id ? "Edit Ticket" : "Add Ticket"}</h2>;

    return (
      <div>
        {title}
        <form onSubmit={this.handleSubmit}>
          <table>
            <thead />
            <tbody>
              <tr>
                <td>Ticket ID</td>
                <td>{ticket.id}</td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="summary">Summary</label>
                </td>
                <td>
                  <input
                    type="text"
                    name="summary"
                    id="summary"
                    value={ticket.summary || ""}
                    onChange={this.handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="body">Body</label>
                </td>
                <td>
                  <input
                    type="text"
                    name="body"
                    id="body"
                    value={ticket.body || ""}
                    onChange={this.handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="requesterUserId">Requester</label>
                </td>
                <td>
                  <input
                    type="number"
                    name="requesterUserId"
                    id="requesterUserId"
                    value={ticket.requester == null ? ticket.requesterUserId : ticket.requester.id}
                    onChange={this.handleRequesterChange}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="assigneeUserId">Assignee</label>
                </td>
                <td>
                  <input
                    type="number"
                    name="assigneeUserId"
                    id="assigneeUserId"
                    value={ticket.assignee == null ? ticket.assigneeUserId : ticket.assignee.id}
                    onChange={this.handleAssigneeChange}
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <button type="sumbit">Save</button>
          <Link to="/tickets">Cancel</Link>
        </form>
      </div>
    );
  }
}

export default withRouter(TicketEdit);
