import React, { Component } from 'react';
import axios from "axios";
class Signin extends Component{
  constructor(props){
    super(props);
    this.state = {login: '',password:''};
    this.handleChangePassword = this.handleChangePassword.bind(this);
    this.handleChangeLogin = this.handleChangeLogin.bind(this);
  }

  handleChangeLogin(event) {
    this.setState({login: event.target.value});
  }
  handleChangePassword(event) {
    this.setState({password: event.target.value});
  }
  reponse_login(reponse){
    if(reponse.data["status"]==="error")
      {
        this.setState({status:"error",texteerror:reponse.data["description"]})
      }

    else{
      console.log("je rentre")
      this.setState({status:"ok"})
    }
  }

  send(){
    console.log("send")
    axios.get("http://localhost:8080/TwisterF/createUser",{params:{login:this.state.login,password:this.state.password}})
    .then(reponse=>this.reponse_login(reponse))
  }



  render(){
    return (
      <div >
      <p className="centerL"> INSCRIPTION </p>
        <label>
          login
          <input type="text" value={this.state.login} onChange={this.handleChangeLogin} className="form-control"/>
            password
          <input type="text"  value={this.state.password} onChange={this.handleChangePassword} className="form-control"/>

        <button type="submit" value="sign-in" onClick={()=>this.send()}  > sign-in  </button>
          </label>
      </div>

        /*  <button className="btn btn-secondary" onClick={this.props.Login}>
            connexion
          </button>
          onClick={this.send()}  onClick={this.props.Login}*/


      );
  }
}
export default Signin;
