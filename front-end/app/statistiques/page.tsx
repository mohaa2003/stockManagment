import Aside from "@/app/sections/aside/aside";
import Section from "../sections/section/section";

const Statistiques : React.FC = () => {
    return(
        <main className="h-screen grid rounded-lg">
            <Aside/>
            <Section title={"Statistiques"} tableHeaders={[]} tableContent={[]}/>
        </main>
    );
}
export default Statistiques;