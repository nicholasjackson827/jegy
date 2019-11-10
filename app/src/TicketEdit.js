import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import Navbar from "./Navbar";
import UserPicker from "./UserPicker";

class TicketEdit extends Component {
  emptyUser = {
    id: "",
    firstName: "",
    lastName: "",
    email: ""
  };

  emptyTicket = {
    summary: "",
    body: "",
    requester: { ...this.emptyUser },
    assignee: { ...this.emptyUser }
  };

  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      id: 0,
      ticket: this.emptyTicket
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleRequesterChange = this.handleRequesterChange.bind(this);
    this.handleAssigneeChange = this.handleAssigneeChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    const id = this.props.match.params.id;
    if (id !== "new") {
      const data = await fetch(`/api/ticket/${id}`);
      const ticket = await data.json();
      this.setState({ id: id, ticket: ticket, isLoading: false });
    }
  }

  handleChange(event) {
    const { value, name, type } = event.target;
    let ticket = { ...this.state.ticket };
    ticket[name] = type === "number" ? parseInt(value, 10) : value;
    this.setState({ ticket });
  }

  handleRequesterChange(newRequester) {
    let ticket = { ...this.state.ticket };
    ticket.requester = newRequester;
    this.setState({ ticket });
  }

  handleAssigneeChange(newAssignee) {
    let ticket = { ...this.state.ticket };
    ticket.assignee = newAssignee;
    this.setState({ ticket });
  }

  async handleSubmit(event) {
    event.preventDefault();
    const { ticket } = this.state;
    const {id } = this.state.id;
    let form = { summary: ticket.summary,
      body: ticket.body,
      requesterUserId: ticket.requester.id,
      assigneeUserId: ticket.assignee.id
    }

    console.log(JSON.stringify(form));
    await fetch("/api/ticket" + (ticket.id ? "/" + ticket.id : ""), {
      method: ticket.id ? "PUT" : "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(form)
    });
    this.props.history.push("/tickets");
  }

  render() {
    const { ticket } = this.state;
    const id = this.state.id;
    const title = <h2>{id ? "Edit Ticket" : "Add Ticket"}</h2>;

    return (
      <div>
        <Navbar />
        {title}
        <form onSubmit={this.handleSubmit}>
          <table>
            <thead />
            <tbody>
              <tr>
                <td>Ticket ID</td>
                <td>{id}</td>
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
                  <label htmlFor="requester">Requester</label>
                </td>
                <td>
                  {!this.state.isLoading ? (
                    <UserPicker
                      id="requester"
                      user={ticket.requester}
                      handleNewUser={this.handleRequesterChange}
                    />
                  ) : null}
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="assignee">Assignee</label>
                </td>
                <td>
                  {!this.state.isLoading ? (
                    <UserPicker
                      id="assignee"
                      user={ticket.assignee}
                      handleNewUser={this.handleAssigneeChange}
                    />
                  ) : null}
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
