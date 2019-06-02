import React, { Component } from "react";
import "./App.css";
import TicketList from "./TicketList";
import TicketEdit from "./TicketEdit";
import Home from "./Home";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path="/" exact={true} component={Home} />
          <Route path="/tickets" exact={true} component={TicketList} />
          <Route path="/tickets/:id" component={TicketEdit} />
        </Switch>
      </Router>
    );
  }
}

export default App;