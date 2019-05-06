import React, { Component } from 'react';
//import './Login.css';
import axios from "axios";
class Logout extends Component{
  constructor(props){
    super(props);
    this.state={key_val:''}

  }
  reponse_logout(reponse){
    //console.log(reponse.data["status"]== "connecte")
    if(reponse.data["status"] == "D2CONNEXION"){
        console.log("déconnexion reussie")
        this.props.Logout()
    }
    else{
        alert("psa déconecté");
        this.setState({status:"error",texteerror:reponse.data["description"]})
    }
  }

  send(){
    console.log("send")
    axios.get("http://localhost:8080/TwisterF/logout",{params:{key:this.props.Key}})
    .then(reponse=>this.reponse_logout(reponse))
  }
  render(){


    return (
        <div>
        <p className="centerL">
        Bienvenue
        </p>
        <button type="submit" value="login"  onClick={()=>this.send()}  > se déconnecter  </button>
        </div>
      );
    }
  }
export default Logout;
