import React, {useEffect} from "react";
import {useDispatch} from "../../store/store";
import {fetchTechnologies} from "../../store/technologies/actions";
import {selectTechnologies} from "../../store/technologies/technologies.slice";
import {useSelector} from "react-redux";

const TechnologiesList = () => {
    let dispatch = useDispatch();
    const technologies = useSelector(selectTechnologies)

    useEffect(() => {
        dispatch(fetchTechnologies())
    }, [])


    return <div>
        {technologies.map((technology) => <p>{technology.name}</p>)}
    </div>
}
export default TechnologiesList;