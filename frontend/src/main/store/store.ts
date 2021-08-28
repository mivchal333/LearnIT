import {configureStore} from "@reduxjs/toolkit";
import questionsReducer from './questions/questions.slice'
import {useDispatch as useOriginalDispatch} from "react-redux";

const store = configureStore({
    reducer: {
        questions: questionsReducer,
    },
})
export type RootState = ReturnType<typeof store.getState>

export type Dispatch = typeof store.dispatch
export const useDispatch = () => useOriginalDispatch<Dispatch>()

export default store