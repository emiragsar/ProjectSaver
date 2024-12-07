import React from 'react'
import SideBar from './SideBar'
import '../css/project.css'
import '../css/proje.css'
import { useState, useEffect } from 'react';
import axios from 'axios';

const BASE_URL = "http://localhost:8080";


function Studentproject() {
    const [userProject, setUserProject] = useState(null);
    const [files, setFiles] = useState([]);

    const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"));



    // Dosya seçildiğinde çağrılır
    const handleFileChange = async (e) => {
        const selectedFile = e.target.files[0];
        if (!selectedFile) return;

        const formData = new FormData();
        formData.append("file", selectedFile);

        try {
            // Dosyayı backend'e gönder
            await axios.post(`${BASE_URL}/file/${userProject.id}/upload`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            });


            const updatedFiles = await axios.get(`${BASE_URL}/file/${userProject.id}`);
            setFiles(updatedFiles.data);
        } catch (error) {
            console.error("File upload failed:", error);
        }
    };


    useEffect(() => {
        const handleDownload = (fileName) => {
            // İndirme işlemi
            axios({
                url: `${BASE_URL}/file/uploads/${fileName}`,
                method: 'GET',
                responseType: 'blob',
            }).then((response) => {

                const url = window.URL.createObjectURL(new Blob([response.data]));
                const a = document.createElement('a');
                a.href = url;
                a.download = fileName;
                document.body.appendChild(a);
                a.click();
                a.remove();
            }).catch((error) => {
                console.error("Download error:", error);
            });
        };
        const fetchUserProject = async () => {
            try {
                const response = await axios.get(`${BASE_URL}/project/list`);
                const project = response.data.find((project) =>
                    project.studentId.includes(loggedInUser.username)
                );
                setUserProject(project || null);


                if (project) {
                    const fileResponse = await axios.get(`${BASE_URL}/file/${project.id}`);
                    setFiles(fileResponse.data);
                }
            } catch (error) {
                console.error("Error fetching user project:", error);
            }
        };
        handleDownload();
        fetchUserProject();

    }, [loggedInUser.username]);
    if (userProject === null) {
        return <div>There is No Any project that you have signed</div>;
    } else if (userProject === undefined) {
        return <div>No project found for this user.</div>;
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
                        <h1>{userProject.projectName}</h1>
                        <div className='all'>
                            <div className='studentList'>
                                <h3>Student List:</h3>
                                <ul>
                                    {userProject.studentId.map((id, index) => (
                                        <li key={index}>Student ID: {id}</li>
                                    ))}
                                </ul>
                            </div>
                            <div className='desc'>

                                <h2>Project Description</h2>
                                <p>{userProject.projectDescription} </p>

                            </div>
                            <div className='fileList'>
                                <h3>Choose a File</h3>
                                <ul>
                                    <input type="file" multiple onChange={handleFileChange} />
                                </ul>
                                <ul>
                                    {userProject.files?.map((file, index) => (
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
    )
}
export default Studentproject 