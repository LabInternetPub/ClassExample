package cat.tecnocampus.webclassexample.repositories;

import domain.NoteLab;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class NoteLabDAO {
    JdbcTemplate jdbcTemplate;

    private final String NUM_NOTES = "select count(*) from note_lab";
    private final String QUERY_BY_ID = "select * from note_lab where id = ?";
    private final String QUERY_ALL = "select * from note_lab";
    private final String QUERY_BY_TITLE = "select * from note_lab where title = ?";
    private final String INSERT_NOTE = "INSERT INTO note_lab (title, content, date_creation, date_edit) VALUES (?, ? , ?, ?)";

    public NoteLabDAO(JdbcTemplate jdbcTemplate) { //spring injects jdbcTemplate and dataSource
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<NoteLab> mapper = (resultSet, i) -> {
        NoteLab noteLab = new NoteLab(resultSet.getString("title"), resultSet.getString("content"));
        noteLab.setDateCreation(resultSet.getTimestamp("date_creation").toLocalDateTime());
        noteLab.setDateEdit(resultSet.getTimestamp("date_edit").toLocalDateTime());

        return noteLab;
    };

    public int getNumNotes() {
        return jdbcTemplate.queryForObject(NUM_NOTES, Integer.class);
    }

    public NoteLab getNoteById(int id) {
        return jdbcTemplate.queryForObject(QUERY_BY_ID, new Object[]{id}, mapper);
    }

    public List<NoteLab> getAllNotes() {
        return jdbcTemplate.query(QUERY_ALL, mapper);
    }

    public List<NoteLab> getNotesByTitle(String title) {
        return jdbcTemplate.query(QUERY_BY_TITLE, new Object[]{title}, mapper);
    }

    public int saveNoteLab(NoteLab noteLab) {
        return jdbcTemplate.update(INSERT_NOTE, noteLab.getTitle(), noteLab.getContent(), Timestamp.valueOf(noteLab.getDateCreation()),
                Timestamp.valueOf(noteLab.getDateEdit()));
    }

    public int[] saveNoteLabList(List<NoteLab> noteLabList) {
        return jdbcTemplate.batchUpdate(INSERT_NOTE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                NoteLab noteLab = noteLabList.get(i);
                preparedStatement.setString(1, noteLab.getTitle());
                preparedStatement.setString(2, noteLab.getContent());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(noteLab.getDateCreation()));
                preparedStatement.setTimestamp(4, Timestamp.valueOf(noteLab.getDateEdit()));
            }

            @Override
            public int getBatchSize() {
                return noteLabList.size();
            }
        });
    }

}
