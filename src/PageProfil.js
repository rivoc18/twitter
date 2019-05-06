import React, { Component } from 'react';
import axios from "axios";
class PageProfil extends Component{
  constructor(props){
    super(props);
          this.state = {id_ajoute:'',id_supp:''};
          this.handleChangeid_ajoute = this.handleChangeid_ajoute.bind(this);
          this.ajouter=this.ajouter.bind(this);
          this.supprimer=this.supprimer.bind(this);
          this.listFriend=this.listFriend.bind(this);
          this.handleChangeid_supp=this.handleChangeid_supp.bind(this);
          this.state={listemess:[]};


  }
  handleChangeid_ajoute(event) {
    this.setState({id_ajoute: event.target.value});
  }
  handleChangeid_supp(event) {
    this.setState({id_supp: event.target.value});
  }
  reponse_pageProfil(reponse){
    //console.log(reponse.data["status"]== "connecte")
    if(reponse.data["friend_ajoute"] == "OK"){
        console.log(reponse.data["friend_ajoute"])

    }
    else if(reponse.data["friend_remove"] == "OK"){
        console.log(reponse.data["friend_ajoute"])


    }
    else if(reponse.data["users"] != null){
        console.log(reponse.data["users"])
        this.setState({listemess:reponse.data["users"]})
        console.log(this.state.listemess)

    }
    else{
        alert(" mauvaise id");
        this.setState({status:"error",texteerror:reponse.data["description"]})
    }
  }

  ajouter(){
    console.log("ajouté")
    axios.get("http://localhost:8080/TwisterF/addFriend",{params:{key:this.props.Key,id_friend:this.state.id_ajoute}})
    .then(reponse=>this.reponse_pageProfil(reponse))
  }
  supprimer(){
    console.log("supprimer")
    axios.get("http://localhost:8080/TwisterF/removeFriend",{params:{cle:this.props.Key,id:this.state.id_supp}})
    .then(reponse=>this.reponse_pageProfil(reponse))
  }
  listFriend(){
    console.log("listFriend")
    axios.get("http://localhost:8080/TwisterF/listFriends",{params:{key:this.props.Key}})
    .then(reponse=>this.reponse_pageProfil(reponse))
  }


  render(){
    		console.log("pageprofil")
    return (

      <div className="container-fluid ">
        <div>
              <label>
                 ajouter un ami
                 <input type="text"  value={this.state.id_ajoute} onChange={this.handleChangeid_ajoute} className="form-control" placeholder="id ami"/>

                <button type="submit"  onClick={()=>this.ajouter()} > ajouter  </button>
              </label>
              <label>
                 supprimer un ami
                <input type="text"  value={this.state.id_supp} onChange={this.handleChangeid_supp} className="form-control" placeholder="id ami"/>

                <button type="submit" onClick={()=>this.supprimer()}> supprimer  </button>
              </label>
        </div>
            <div className="container ">
            <button type="submit"  onClick={()=>this.listFriend()}  > refresh amis  </button>
            <ul>
            liste amis
            {this.state.listemess.map(item => (
                  <li >{item.id}</li>
              ))}
              </ul>
            </div>
      </div>


    );
  }
}
export default PageProfil;
