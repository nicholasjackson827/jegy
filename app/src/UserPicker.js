import React, { Component } from "react";

export default class UserPicker extends Component {
  emptyUser = {
    id: "",
    firstName: "",
    lastName: "",
    email: ""
  };

  constructor(props) {
    super(props);
    this.state = {
      allUsers: [],
      user: { ...this.emptyUser }
    };
    this.handleChange = this.handleChange.bind(this);
  }

  async componentDidMount() {
    const data = await fetch("/api/users");
    const users = await data.json();
    users.sort((a, b) => {
      let nameA = a.lastName.toUpperCase();
      let nameB = b.lastName.toUpperCase();
      if (nameA === nameB) return 0;
      return nameA > nameB ? 1 : -1;
    });
    await this.setState({ allUsers: users });

    if (this.props.user && this.props.user.id !== "") {
      const user = users.filter(user => user.id === this.props.user.id)[0];
      this.setState({ user: user });
    }
  }

  handleChange(event) {
    const { value } = event.target;
    const user = this.state.allUsers.filter(user => user.id === parseInt(value, 10))[0];
    this.setState({ user: user });
    this.props.handleNewUser(user);
  }

  render() {
    const { user, allUsers } = this.state;
    return (
      <select value={user == null ? "UNASSIGNED" : user.id} onChange={this.handleChange}>
        <option value="UNASSIGNED">UNASSIGNED</option>
        {allUsers.map(user => (
          <option key={user.id} value={user.id}>{`${user.firstName} ${user.lastName}`}</option>
        ))}
      </select>
    );
  }
}
