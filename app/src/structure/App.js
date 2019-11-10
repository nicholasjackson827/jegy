import React, { Component } from "react";
import TicketList from "../tickets/TicketList";
import TicketEdit from "../tickets/TicketEdit";
import Home from "./Home";
import Admin from "../admin/Admin";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path="/" exact={true} component={Home} />
          <Route path="/tickets" exact={true} component={TicketList} />
          <Route path="/tickets/:id" component={TicketEdit} />

          <Route path="/admin" exact={true} component={Admin} />
        </Switch>
      </Router>
    );
  }
}

export default App;
