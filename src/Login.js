import React, { Component } from 'react';
import axios from "axios";

import './Login.css';
class Login extends Component{
  constructor(props) {
      super(props);
      this.state = {log: '',password:''};

      this.handleChangePassword = this.handleChangePassword.bind(this);
      this.handleChangeLogin = this.handleChangeLogin.bind(this);
      this.send = this.send.bind(this);
    }

    handleChangeLogin(event) {
      this.setState({log: event.target.value});
    }
    handleChangePassword(event) {
      this.setState({password: event.target.value});
    }

  reponse_login(reponse){
    //console.log(reponse.data["status"]== "connecte")
    if(reponse.data["status"] == "connecte"){
        this.setState({key: reponse.data["key ="]})
        this.props.Login()
        console.log(this.props.Login)
        this.props.sendData(reponse.data["key ="])
        console.log(reponse.data["key ="])
        console.log("key",reponse.data)
    }
    else{
        alert("mauvais login ou mdp");
        this.setState({status:"error",texteerror:reponse.data["description"]})
    }
  }

  send(){
    console.log("send")
    axios.get("http://localhost:8080/TwisterF/login",{params:{login:this.state.log,password:this.state.password}})
    .then(reponse=>this.reponse_login(reponse))
  }
  affichage(){
    console.log("ll")
  }


  render() {
    return (
      <div className="bordure">
        <label >
        <p className="centerL"> CONNEXION </p>
          login
          <input type="text"  onChange={this.handleChangeLogin} className="form-control"/>

            password
        <input className="retour" type="text"  onChange={this.handleChangePassword} className="form-control"/>

        <button  className="retour" type="submit" value="login"  onClick={()=>this.send()}  > se connecter  </button>
      </label>
      </div>

        /*  <button className="btn btn-secondary" onClick={this.props.Login}>
            connexion
          </button>
          onClick={this.send()}  onClick={this.props.Login}*/


      );
  }
}
export default Login;
