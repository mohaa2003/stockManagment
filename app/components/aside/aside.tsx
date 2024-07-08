import { MdSettings } from "react-icons/md";
import { MdLogout } from "react-icons/md";


const Aside : React.FC = () => {
    return(
        <aside className="h-full bg-slate-800 flex-col rounded-l-lg">
            <div className="topAside h-4/5">
                
            </div>
            <div className="bottomAside h-1/5 flex flex-col justify-evenly items-center">
                <MdSettings color="white" size={26} cursor={"pointer"}/>
                <MdLogout color="white" size={26} cursor={"pointer"}/>
            </div>
        </aside>
    );
}

export default Aside;