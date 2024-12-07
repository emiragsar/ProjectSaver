import React, { useEffect, useState } from 'react';
import './app.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const apiUrl = 'http://localhost:8080/users/login';



function Container() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();



    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(apiUrl, {
                username,
                password,
            });


            if (response.status !== 200) {
                throw new Error('Login failed. Invalid credentials or server error.');
            }


            const user = response.data;
            console.log(user.username, user.password, user.role, user.id);


            localStorage.setItem('loggedInUser', JSON.stringify(user));
            localStorage.setItem('isAuthenticated', 'true');
            localStorage.setItem('role', user.role);
            localStorage.setItem('username', user.username);
            localStorage.setItem('userid', user.id);


            if (user.role === 'student') {
                navigate('/student-project');
            } else {
                navigate('/my-projects');
            }

        } catch (error) {
            alert('Login failed: ' + error.message);
            console.error('Login error:', error);
        }
    };

    return (
        <div>
            <div className='container'>
                <div className="login-page">
                    <div className="image-probee">
                        <img src="ProBee.png" className='proBee' alt="" />
                    </div>
                    <div className="page">
                        <div className='write'>
                            <h1 className='baslik'>Log In</h1>
                            <p className='yaz'>Sign in to continue</p>
                        </div>
                        <div className='usern'>
                            <p className='user-name'>USERNAME</p>
                            <input value={username} onChange={(e) => setUsername(e.target.value)} className='user-input-name' type="text" />
                        </div>
                        <div className="userp">
                            <p className='user-password'>PASSWORD</p>
                            <input value={password} onChange={(e) => setPassword(e.target.value)} className='user-input-password' type="password" />
                        </div>
                        <div className='login-button'>
                            <button onClick={handleLogin} className='login'>Log in</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Container;