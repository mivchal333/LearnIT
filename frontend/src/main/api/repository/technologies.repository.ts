import axios from "axios";

const fetchTechnologies = () => axios.get("/technology")

export default {fetchTechnologies};