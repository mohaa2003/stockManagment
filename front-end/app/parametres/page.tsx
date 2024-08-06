import Aside from "@/app/sections/aside/aside";
import Section from "../sections/section/section";

const Parametres : React.FC = () => {
    return(
        <main className="h-screen grid rounded-lg">
            <Aside/>
            <Section title={"Parametres"} tableHeaders={[]} tableContent={[]}/>
        </main>
    );
}
export default Parametres;