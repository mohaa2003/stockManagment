import Aside from "@/app/sections/aside/aside";
import Section from "../sections/section/section";

const Dettes : React.FC = () => {
    return(
        <main className="h-screen grid rounded-lg">
            <Aside/>
            <Section title={"Mes Dettes"} tableHeaders={[]} tableContent={[]}/>
        </main>
    );
}
export default Dettes;