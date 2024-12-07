import React, { useEffect, useState } from 'react';
import SideBar from './SideBar';
import '../css/projects.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const BASE_URL = "http://localhost:8080";

function NewProject({ users }) {
    const [projectName, setProjectName] = useState('');
    const [projectDescription, setProjectDescription] = useState('');
    const [studentCount, setStudentCount] = useState('');
    const [studentId, setStudentId] = useState([]);
    const [userId1, setUserId1] = useState([])

    useEffect(() => {
        const usernames = users.map(user => user.username);
        setUserId1(usernames);
    }, [users]);

    const navigate = useNavigate();

    const handleSelectionChange = (event) => {
        setStudentCount(Number(event.target.value));
        setStudentId(Array(Number(event.target.value)).fill(''));
    };

    const handleInputChange = (index, event) => {
        const newId = [...studentId];
        newId[index] = event.target.value;
        setStudentId(newId);
    };


    const create = async (e) => {
        e.preventDefault();

        const userId = localStorage.getItem('userid');

        try {
            if (!projectName || !projectDescription) {
                alert('Please fill in all required fields.');
                return;
            }
            const invalidIds = studentId.filter(id => !userId1.includes(id));
            if (invalidIds.length > 0) {
                alert(`Invalid Student ID(s): ${invalidIds.join(', ')}`);
                return;
            }
            const response = await axios.post(`${BASE_URL}/project/create`, {
                projectName,
                projectDescription,
                studentId,
                user: { id: userId }
            });

            if (response.status === 200 || response.status === 201) {
                alert('Project created successfully!');
                navigate('/my-projects');
            } else {
                console.error('Unexpected status code:', response.status);

                alert('Project creation failed. Please try again.');
            }
        } catch (error) {
            console.error("There was an error while creating the project:", error);
            alert('Error creating project. Please try again.');

        }
    };

    return (
        <div>
            <div className='hepsi'>
                <div className='side'>
                    <SideBar />
                </div>
                <div className='pmain-content'>
                    <div className='title'>
                        <h1>New Project</h1>
                    </div>
                    <div className='project-name'>
                        <h2>Project Name :</h2>
                        <input value={projectName} onChange={(e) => setProjectName(e.target.value)} type="text" />
                    </div>
                    <div className='project-summary'>
                        <h2>Description :</h2>
                        <textarea value={projectDescription} onChange={(e) => setProjectDescription(e.target.value)} />
                    </div>
                    <div className='student-number'>
                        <h2>Student Number :</h2>
                        <select value={studentCount} onChange={handleSelectionChange}>
                            <option value="">Choose</option>
                            <option value="1">1 Student</option>
                            <option value="2">2 Students</option>
                            <option value="3">3 Students</option>
                            <option value="4">4 Students</option>
                        </select>
                    </div>
                    {studentCount > 0 && (
                        <div className='idt'>
                            {Array.from({ length: studentCount }, (_, index) => (
                                <div className='id' key={index}>
                                    <input placeholder='Student ID' type="text" value={studentId[index]} onChange={(e) => handleInputChange(index, e)} />
                                </div>
                            ))}
                        </div>
                    )}
                    <div className='submit'>
                        <button onClick={create}>Create</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default NewProject;
