import React, { Component } from 'react';
import './MainPage.css';
import NavigationPannel from './NavigationPannel';
import PageProfil from './PageProfil';
import logo from './logo.png'
import axios from "axios";
import Message from './Message';
class MainPage extends Component{
	constructor(props){
		super(props);
		this.state={ isConnected : false };
		this.state= {key:''}
	  this.getConnected = this.getConnected.bind(this);
	  this.setLogout = this.setLogout.bind(this);
		this.handleClick=this.handleClick.bind(this);
		this.getData=this.getData.bind(this);


		axios.get("http://localhost:8080/TwisterF/Hello").then(response => console.log(response))
	}
	getData(val){
			this.setState({key: val});
			console.log(this.state.key);
	}
	getConnected(){
		this.setState(state => ({
	    isConnected: true
	  }));
				console.log(this.isConnected)
	}
	setLogout(){
	  this.setState(state => ({
	    isConnected: false
	  }));
	}
	handleClick() {
	 this.setState(state => ({
		 isConnected: !state.isConnected
	 }));
 }
	 consoletest(){
	  console.log(this.state.isConnected)
	}

	render(){
		return (
		 	<div className="container-fluid" >
					<header className="row"  >
						<div className ="col-sm-2" >
								<img src={logo} height="80" width="80"/>
						</div>
						<div className ="col-sm-10 center "> TWISTER</div>

					</header>
					<div className="row   ">
										<div className ="col-sm-4 " >
										 <NavigationPannel  login={this.getConnected} logout={this.setLogout}
										 	isConnected={this.state.isConnected} sendData={this.getData}/>

											{ this.state.isConnected == true ?
				             		<PageProfil Key={this.state.key}/>:""

				              }


					</div>
						{ this.state.isConnected == true ?

					  <div className ="col-sm-8 " >
								<Message Key={this.state.key} className="row  h-50 "/>


					  </div>
						: <p className="centerV"> VEUILLEZ VOUS CONNECTER</p>
					}
					</div>
				</div>
		  );

	}

}

export default MainPage;
