import React, { Component } from "react";
import "./App.css";
import { Link } from "react-router-dom";

class Home extends Component {
  render() {
    return (
      <div>
        <button>
          <Link to="/tickets">See Tickets</Link>
        </button>
      </div>
    );
  }
}

export default Home;
