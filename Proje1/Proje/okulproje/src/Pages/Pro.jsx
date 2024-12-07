import React from 'react'

import '../css/myproject.css'



function Pro({ projectId, projectName }) {
    

    return (

        <div className='project'>
            <div className='proid'>

                <div className='proname'>
                    <a href="">
                        <h2>{projectId} - {projectName} </h2>
                    </a>

                </div>
                <div className='project-line'>
                </div>
            </div>

        </div>

    )
}

export default Pro