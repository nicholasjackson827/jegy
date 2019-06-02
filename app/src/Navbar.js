import React, { Component } from "react";
import { Link } from "react-router-dom";

class Navbar extends Component {
  render() {
    return (
      <div>
        <Link to={"/tickets"}>Tickets</Link>
        &nbsp;
        <Link to={"/admin"}>Admin</Link>
        <hr />
      </div>
    );
  }
}

export default Navbar;
