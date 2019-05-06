import React, { Component } from 'react';
import './NavigationPannel.css';
import Login from './Login';
import Logout from './Logout';
import Signin from './Signin';
class NavigationPannel extends Component{
  constructor(props){
		super(props);
    this.state= {key:''}
    this.getData = this.getData.bind(this);
  }
  getData(val){
      console.log(val);
      this.setState({key: val});
      this.props.sendData(val)
      console.log(this.state.key);
  }


  render(){
    return (
      <nav>

             { this.props.isConnected == true ?
               <div>

                 <Logout Logout={this.props.logout} Key={this.state.key}  />
               </div>
              :
             <div>
               <Login className="row "Login={this.props.login} sendData={this.getData}  />
               <Signin className="row "/>
             </div>
             }
      </nav>
   );
 }
}
export default NavigationPannel;
