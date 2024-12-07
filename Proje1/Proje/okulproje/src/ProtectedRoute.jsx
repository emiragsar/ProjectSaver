import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';

const ProtectedRoute = ({ children }) => {
    const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';
    const role = localStorage.getItem('role');
    const location = useLocation();


    if (!isAuthenticated) {
        return <Navigate to="/" replace />;
    }


    const restrictedRoutes = {
        student: ['/new-project'],
    };

    if (restrictedRoutes[role]?.includes(location.pathname)) {
        const redirectPath = role === 'student' ? '/student-project' : '/';
        return <Navigate to={redirectPath} replace />;
    }


    return children;
};

export default ProtectedRoute;
