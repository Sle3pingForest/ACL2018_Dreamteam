-terminal 
-bash start.bat ou .\start.sh

 <target name="Jeu">
        <java classname="Jeu" failonerror="true" fork="yes">
            <jvmarg line="-Djava.library.path=lib/natives"/>
            <classpath refid="ACL2018_Dreamteam.classpath"/>
        </java>
    </target>
