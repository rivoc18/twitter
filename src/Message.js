import React, { Component } from 'react';
import axios from "axios";

//import './Login.css';
class Message extends Component{
  constructor(props) {
      super(props);
      this.state={message:""};
      this.state={listemess:[]};

      this.ajouter = this.ajouter.bind(this);
      this.handleChangeMess = this.handleChangeMess.bind(this);
  }

  reponse_envoyer(reponse){
    console.log(reponse.data)
    if(reponse.data["status"] == "message envoye"){
        console.log("message envoyer")
    }
    else{
        alert("message non envoyé");
        this.setState({status:"error",texteerror:reponse.data["description"]})
    }
  }
  reponse_affiche(reponse){
    console.log(reponse.data["mess"])
    this.setState({listemess:reponse.data["mess"]})

    this.setState({size:reponse.data["size"]})
  }

  ajouter(){
    console.log("ajoute")
    axios.get("http://localhost:8080/TwisterF/addComment",{params:{key:this.props.Key,message:this.state.message}})
    .then(reponse=>this.reponse_envoyer(reponse))
  }
  affiche(){
    console.log("affiche")
    axios.get("http://localhost:8080/TwisterF/getmessage")
    .then(reponse=>this.reponse_affiche(reponse))
  }

  handleChangeMess(event) {
    this.setState({message: event.target.value});
  }

  render(){
      return(
          <div >
                <label>
                  inserer un Message
                  <input type="text"  onChange={this.handleChangeMess}/>
                  <button type="submit"  onClick={()=> this.ajouter() } > envoyer  </button>
                </label>
                <button type="submit"  onClick={()=> this.affiche() } > refresh mess  </button>
                <p className="centerL"> LISTE MESSAGES </p>
                {this.state.listemess.map(item => (
                      <li >{item[2] } {item[3]}</li>
                  ))}

          </div>
      );
  }
}

export default Message;
