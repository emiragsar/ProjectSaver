import React from 'react';
import { useParams } from 'react-router-dom';
import SideBar from './SideBar';
import '../css/project.css';

function Project({ projects }) {

    const { projectId } = useParams();
    const numericProjectId = parseInt(projectId, 10);
    const project = projects.find(p => p.id === numericProjectId);


    if (!project) {
        return <div>Project not found.</div>;
    }

    return (
        <div>
            <div className='hepsi'>
                <div className='side'>
                    <SideBar />
                </div>
                <div className="project">
                    <div className='title'>
                        <h1>Project Detail</h1>
                    </div>
                    <div className='project1'>
                        <h1>{project.projectName}</h1>
                        <div className='all'>
                            <div className='studentList'>
                                <h3>Student List:</h3>
                                <ul>
                                    {project.studentId.map((id, index) => (
                                        <li key={index}>Student ID: {id}</li>
                                    ))}
                                </ul>
                            </div>
                            <div className='desc'>
                                <h2>Project Description</h2>
                                <p>{project.projectDescription}</p>
                            </div>
                            <div className='fileList'>
                                <h3>Uploaded Files</h3>
                                <ul>

                                    {project.files?.map((file, index) => (
                                        <li key={index}>
                                            <a href={`http://localhost:8080/file/uploads/${file.fileName}`}
                                                download
                                                target="_blank"
                                                rel="noopener noreferrer">{file.originalFileName}</a>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Project;
