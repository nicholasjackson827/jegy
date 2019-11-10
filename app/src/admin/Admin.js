import React, { Component } from "react";
import Navbar from "../common/Navbar";

class Admin extends Component {
  constructor(props) {
    super(props);
    this.state = {
      tableToClear: "",
      responseConsole: []
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleClearDb = this.handleClearDb.bind(this);
    this.handleSeedDb = this.handleSeedDb.bind(this);
  }

  handleChange(event) {
    const { value } = event.target;
    this.setState({ tableToClear: value });
  }

  async handleClearDb(event) {
    event.preventDefault();
    const { tableToClear } = this.state;

    await fetch(`/api/dev/clearDb${tableToClear.length ? "?table=" + tableToClear : ""}`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    }).then(async response => {
      let responseConsole = this.state.responseConsole;
      responseConsole.push(new Date().toLocaleString() + ": " + (await response.text()));
      this.setState({ responseConsole: responseConsole });
    });
  }

  async handleSeedDb(event) {
    event.preventDefault();

    await fetch(`/api/dev/seedDb`, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    }).then(async response => {
      let responseConsole = this.state.responseConsole;
      responseConsole.push(new Date().toLocaleString() + ": " + (await response.text()));
      this.setState({ responseConsole: responseConsole });
    });
  }

  render() {
    return (
      <div>
        <Navbar />
        <h2>Database Stuff</h2>

        <h4>Clear DB</h4>
        <form onSubmit={this.handleClearDb} onChange={this.handleChange}>
          <p>Which table should be cleared?</p>
          <input type="radio" id="clearDb-ticket" name="tableToClear" value="ticket" />
          <label htmlFor="clearDb-ticket">Ticket</label>
          <br />
          <input type="radio" id="clearDb-user" name="tableToClear" value="user" />
          <label htmlFor="clearDb-user">User</label>
          <br />
          <input type="radio" id="clearDb-none" name="tableToClear" value="" />
          <label htmlFor="clearDb-none">Everything!</label>
          <br />
          <button type="submit">Clear DB</button>
        </form>

        <h4>Seed DB</h4>
        <form onSubmit={this.handleSeedDb} onChange={this.handleChange}>
          <button type="submit">Seed the DB</button>
        </form>

        {this.state.responseConsole.length > 0 && (
          <div>
            <h2>Response Console</h2>
            <pre>
              {this.state.responseConsole.map(log => (
                <div key={log}>
                  {log}
                  <br />
                </div>
              ))}
            </pre>
          </div>
        )}
      </div>
    );
  }
}

export default Admin;
