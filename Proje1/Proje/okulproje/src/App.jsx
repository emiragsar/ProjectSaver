import { useState, useEffect } from 'react';
import './App.css';
import Container from './Container';
import { Routes, Route } from 'react-router-dom';
import NewProject from './Pages/NewProject';
import MyProjects from './Pages/MyProjects';
import Project from './Pages/Project';
import axios from 'axios';
import ProtectedRoute from './ProtectedRoute';
import Studentproject from './Pages/Studentproject';

const BASE_URL = "http://localhost:8080"

function App() {

  const [projects, setProjects] = useState([]);
  const [users, setUsers] = useState([]);


  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const response = await axios.get(`${BASE_URL}/project/list`);
        const response1 = await axios.get(`${BASE_URL}/users/list`)
        setUsers(response1.data)
        setProjects(response.data);
      } catch (error) {
        console.error("Error fetching projects:", error);
      }
    };
    fetchProjects();
  }, []);






  return (
    <div>
      <Routes>
        <Route path='/' element={<Container />} />
        <Route path='/new-project' element={<ProtectedRoute> <NewProject users={users} /> </ProtectedRoute>} />
        <Route path='/my-projects' element={<ProtectedRoute><MyProjects projects={projects} /> </ProtectedRoute>} />
        <Route path='/project/:projectId' element={<Project projects={projects} />} />
        <Route path='/student-project' element={<ProtectedRoute><Studentproject /></ProtectedRoute>} />


      </Routes>
    </div>
  );
}


export default App;