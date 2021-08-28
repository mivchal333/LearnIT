import React, {useEffect} from 'react';
import {Button} from "@material-ui/core";
import {useDispatch} from "../../store/store";
import {fetchTechnologies} from "../../store/technologies/actions";

const HomePage = () => {
    let dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetchTechnologies())
    }, [])

    return (
        <Button>Hello!</Button>
    );
}

export default HomePage;