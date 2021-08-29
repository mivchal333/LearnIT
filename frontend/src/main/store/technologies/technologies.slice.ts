import {createSlice, PayloadAction} from '@reduxjs/toolkit'
import {Technology} from "../../api/model/Technology.model";
import {RootState} from "../store";


interface TechnologiesSlice {
    technologies: Technology[]
}

const initialState: TechnologiesSlice = {
    technologies: [],
}

const technologiesSlice = createSlice({
    name: 'technologies',
    initialState,
    reducers: {
        setTechnologies: (state, action: PayloadAction<Technology[]>) => {
            state.technologies = action.payload;
        }
    }
})
export const {setTechnologies} = technologiesSlice.actions

export const selectTechnologies = (state: RootState) => state.technologies.technologies
export default technologiesSlice.reducer;