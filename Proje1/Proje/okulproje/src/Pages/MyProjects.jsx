import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import SideBar from './SideBar';
import Pro from './Pro';
const BASE_URL = "http://localhost:8080";





function MyProjects({ projects }) {

    const userId = parseInt(localStorage.getItem('userid'), 10);
    const username = localStorage.getItem('username');


    useEffect(() => {
        console.log(projects)
    }, [])


    const userProjects = projects.filter(
        project => project.user.id === userId || project.studentId.includes(username)
    );

    return (
        <div>
            <div className='hepsi'>
                <div className='side'>
                    <SideBar />
                </div>
                <div>
                    <div className='line'></div>
                </div>

                <div className="main-content">
                    <div className='title'>
                        <h1>My Projects</h1>
                    </div>
                    <div className='project-line'></div>

                    {userProjects.length > 0 ? (
                        userProjects.map((project, index) => (
                            <Link key={index} to={`/project/${project.id}`}>
                                <Pro key={index} projectId={project.id} projectName={project.projectName} />
                            </Link>
                        ))
                    ) : (
                        <p>No projects found.</p>
                    )}
                </div>
            </div>
        </div>
    );

}

export default MyProjects;
