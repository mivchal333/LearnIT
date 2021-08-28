import {createSlice, PayloadAction} from '@reduxjs/toolkit'
import {Question} from "../../api/model/Question.model";


interface QuestionsSlice {
    questions: Question[]
}

const initialState: QuestionsSlice = {
    questions: [],
}

const questionsSlice = createSlice({
    name: 'questions',
    initialState,
    reducers: {
        setQuestions: (state, action: PayloadAction<Question[]>) => {
            state.questions = action.payload;
        }
    }
})
export const {setQuestions} = questionsSlice.actions

export const selectQuestions = (state: QuestionsSlice) => state.questions
export default questionsSlice.reducer;