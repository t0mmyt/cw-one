# Example use

    ➜ sdp-cw-one (shiny) git:(master) λ sbt "run test2.sml"
    [info] Set current project to SML (in build file:/tmp/sdp-cw-one/)
    [info] Updating {file:/tmp/sdp-cw-one/}sdp-cw-one...
    [info] Resolving jline#jline;2.12.1 ...
    [info] Done updating.
    [info] Compiling 12 Scala sources to /tmp/sdp-cw-one/target/scala-2.11/classes...
    [info] Running sml.Machine test2.sml
    SML interpreter - Scala version
    Here is the program; it has 7 instructions.
    f0: lin register 20 value is 6
    f1: lin register 21 value is 1
    f2: lin register 22 value is 1
    f3: mul 21 * 20 to 21
    f4: sub 20 - 22 to 20
    f5: bnz is register 20 zero
    f6: out output 21

    Beginning program execution.
    Output from r21: 720
    Ending program execution.
    Values of registers at program termination:
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 720 1 0 0 0 0 0 0 0 0 0.
    [success] Total time: 7 s, completed 10-Apr-2016 15:01:29

