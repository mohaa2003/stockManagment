import Aside from "@/app/sections/aside/aside";
import Section from "../sections/section/section";

const Achats : React.FC = () => {
    return(
        <main className="h-screen grid rounded-lg">
            <Aside/>
            <Section title={"Les Achats"} tableHeaders={["salam","elikoum","aa rais"]} tableContent={[]}/>
        </main>
    );
}
export default Achats;