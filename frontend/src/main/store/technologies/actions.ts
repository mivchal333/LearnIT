import {Dispatch} from "../store";
import {setTechnologies} from "./technologies.slice";
import TechnologiesRepository from '../../api/repository/technologies.repository'

export const fetchTechnologies = () => async (dispatch: Dispatch) => {
    const {data} = await TechnologiesRepository.fetchTechnologies();
    dispatch(setTechnologies(data))
}