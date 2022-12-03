import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Button } from '@mui/material';
import { Footer, Header } from './Components/Common';

function App() {
  const showAlert = () => {
    alert('YoooHoooo!');
  }

  return (
    <div className="App">
      <Header />
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>Welcome to our first app C:</p>
        <Button variant="contained" onClick={showAlert}>Click me :DDD</Button>
      </header>
      <Footer />
    </div>
  );
}

export default App;
