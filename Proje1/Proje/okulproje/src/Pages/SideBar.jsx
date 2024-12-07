import React, { useState, useEffect } from 'react'
import '../css/Sidebar.css'
import { Link, useNavigate } from 'react-router-dom';


function SideBar() {
    const navigate = useNavigate();
    const [userRole, setUserRole] = useState(null);

    useEffect(() => {
        const role = localStorage.getItem('role');
        setUserRole(role);
    }, []);


    const handleLogout = () => {
        localStorage.removeItem('isAuthenticated');
        localStorage.removeItem('role');
        localStorage.removeItem('username');
        localStorage.removeItem('userid');
        navigate('/');
    };
    return (
        <div>
            <div className="hepsi">
                <div className="sidebar">
                    <div className="logo">
                        <img className='probee' src="/Probee.png" alt="" />
                    </div>
                    <div className='side-line'></div>

                    <ul>

                        {userRole === 'instructor' && (
                            <>
                                <li>
                                    <Link to="/new-project">New Project</Link>
                                </li>
                                <div className="side-line"></div>
                            </>
                        )}
                        <li>
                            <Link to="/my-projects">My Projects</Link>
                        </li>
                        <div className="side-line"></div>
                        <li><button onClick={handleLogout}>Logout</button></li>
                    </ul>
                </div>
                <div className='vertical'></div>
            </div>
        </div>
    )
}

export default SideBar